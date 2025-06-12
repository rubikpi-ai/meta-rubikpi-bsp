SUMMARY = "Firmware packages for the qcs9100 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:qcom-base-bsp = " \
    hexagon-dsp-binaries-qcom-sa8775p-ride-adsp \
    hexagon-dsp-binaries-qcom-sa8775p-ride-cdsp \
    hexagon-dsp-binaries-qcom-sa8775p-ride-gdsp \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca2066 \
    linux-firmware-qca-qca61x4-usb \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a660 \
    linux-firmware-qcom-adreno-a663 \
    linux-firmware-qcom-sa8775p-adreno \
    linux-firmware-qcom-sa8775p-audio \
    linux-firmware-qcom-sa8775p-compute \
    linux-firmware-qcom-sa8775p-generalpurpose \
    linux-firmware-qcom-sa8775p-qupv3fw \
    linux-firmware-qcom-vpu \
"
