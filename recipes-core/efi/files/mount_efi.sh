#!/bin/sh

# If boot.mount fails to mount efi,then  mount /dev/mmcblk1p1 to /boot
mount /dev/mmcblk1p1 /boot
