# from py4j.java_gateway import JavaGateway

# gateway = JavaGateway()

# stack = gateway.entry_point.getStack()

# stack.push("First %s" % ('item'))
# stack.push("Second item")
# print(stack.pop())
# print(stack.pop())
# print(stack.pop())

import torch
from matplotlib import pyplot as plt
import numpy as np
import cv2
import asyncio
import io
import sys
from coco_classes import coco_list, coco_classes
import socket
from py4j.java_gateway import JavaGateway

person_counter = 0

async def async_detect():

    print("Function started")
    model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
    cap = cv2.VideoCapture("tolpa.mkv")
    
    
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

    