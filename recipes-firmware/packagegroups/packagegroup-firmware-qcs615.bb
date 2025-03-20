SUMMARY = "Firmware packages for the qcs615 machine"

inherit packagegroup

RRECOMMENDS:${PN} += " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:append:qcom-base-bsp = " \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-qca-qca6698 \
"
