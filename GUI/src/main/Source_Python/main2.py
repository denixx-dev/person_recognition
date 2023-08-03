import torch
from matplotlib import pyplot as plt
import numpy as np
import cv2
import asyncio
import os
import pathlib 
import io
import sys
from coco_classes import coco_list, coco_classes
import socket
from py4j.java_gateway import JavaGateway

person_counter = 0

def start_gateway():
    javaGateway = JavaGateway()
    gui = javaGateway.entry_point
    gui.drawInterface() 
    return javaGateway

async def async_detect(gateway):
    
    print("Function started")
    model = torch.hub.load('ultralytics/yolov5', 'yolov5n')

    gateway.setjButtonEnabled()
    gateway.disableNotReadyLabel()

    while (gateway.getWindowClosedFlag != True):
        print("Waiting for a file being chosen...")
        while not gateway.getFileChosenFlag():
            pass
        print("Excellent! The file was chosen")
        
        video_file = gateway.getFileAbsolutePath()
        cap = cv2.VideoCapture(video_file)
        
        try:
            while cap.isOpened():
                ret, frame = cap.read()
                
                # Make detections 
                results = model(frame)
                asyncio.create_task(count_people(results, gateway))

                if gateway.getCheckBoxState():
                    cv2.imshow('YOLO', np.squeeze(results.render()))
                else:
                    cv2.imshow('YOLO', np.squeeze(frame))
                
                if cv2.waitKey(10) & 0xFF == ord('q'):
                    break

                await asyncio.sleep(0)
            cap.release()
            cv2.destroyAllWindows()
        except:
            cap.release()
            cv2.destroyAllWindows()
        gateway.setFileChosenFlag(False)

async def count_people(results, gateway):
    results = results.pandas().xyxy[0]

    class_counts = {label: 0 for label in coco_classes.keys()}

    for _, result in results.iterrows():
        recognizing = result['class']
        class_counts[recognizing] += 1
    print(f'Persons on a frame: {class_counts[0]}')
    gateway.setCounterLabelText(f"Persons on a frame: {class_counts[0]}")


if __name__ == "__main__":
    try:
        gateway = start_gateway()
        loop = asyncio.get_event_loop()
        loop.run_until_complete(asyncio.gather(async_detect(gateway)))
    except:
        print("Gateway closed connection")

    