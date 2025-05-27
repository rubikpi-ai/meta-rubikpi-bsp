SUMMARY = "Firmware packages for the qcs8300 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:qcom-base-bsp = " \
    hexagon-dsp-binaries-qcom-qcs8300-ride-adsp \
    hexagon-dsp-binaries-qcom-qcs8300-ride-cdsp \
    hexagon-dsp-binaries-qcom-qcs8300-ride-gdsp \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca2066 \
    linux-firmware-qca-qca61x4-usb \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a623 \
    linux-firmware-qcom-adreno-a650 \
    linux-firmware-qcom-qcs8300-adreno \
    linux-firmware-qcom-qcs8300-audio \
    linux-firmware-qcom-qcs8300-compute \
    linux-firmware-qcom-qcs8300-generalpurpose \
    linux-firmware-qcom-vpu \
"
