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
    # path = pathlib.Path(__file__).parent.resolve()
    # parent = pathlib.PureWindowsPath(path)
    # parent = str(parent.parents[0]).replace("\\", "/")
    # print(parent)
    # os.chdir("GUI/src/main/java")
    # os.system(f"/run_main.bat")

    javaGateway = JavaGateway()
    gui = javaGateway.entry_point
    gui.drawInterface() 
    return javaGateway

async def async_detect():
    
    print("Function started")
    model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
    
    
    gateway = start_gateway()

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
            asyncio.create_task(count_people(results))

            cv2.imshow('YOLO', np.squeeze(results.render()))
            
            if cv2.waitKey(10) & 0xFF == ord('q'):
                break

            await asyncio.sleep(0)
        cap.release()
        cv2.destroyAllWindows()
    except:
        cap.release()
        cv2.destroyAllWindows()

async def count_people(results):
    res = 0
    results = results.pandas().xyxy[0]

    class_counts = {label: 0 for label in coco_classes.keys()}

    for _, result in results.iterrows():
        recognizing = result['class']
        class_counts[recognizing] += 1
    print(f'Persons on a frame: {class_counts[0]}')



if __name__ == "__main__":
    loop = asyncio.get_event_loop()
    loop.run_until_complete(asyncio.gather(async_detect()))

    