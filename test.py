import sys
import socket
import time
import logging as log

log.basicConfig(level=log.INFO, filename="c:\pylog\log.log", filemode="w")
log.info('start')

person_counter = 0
HOST = "127.0.0.1"
PORT = 8000

log.info(13)

server1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server1.bind((HOST, PORT))
server1.listen()

log.info(17)

user, address = server1.accept()

log.info(21)

while True:
    log.info(f'iteration {person_counter}')
    user.sendto(f"Persons a frame: {person_counter} ".encode("utf-8"), (HOST, PORT))
    log.info(28)
    person_counter +=1
    if person_counter > 100: 
        log.info('exit')
        sys.exit(1)
    time.sleep(0.1)
