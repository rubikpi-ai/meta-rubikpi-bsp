SUMMARY = "Firmware packages for the qcs9100 machine"

inherit packagegroup

RRECOMMENDS:${PN} = " \
    firmware-qcom-dspso \
    firmware-qcom-hlosfw \
"

RRECOMMENDS:${PN}:qcom-base-bsp = " \
    linux-firmware-ath11k-qca6698aq \
    linux-firmware-ath11k-wcn6855 \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a663 \
    linux-firmware-qcom-sa8775p-audio \
    linux-firmware-qcom-sa8775p-compute \
    linux-firmware-qcom-sa8775p-generalpurpose \
    linux-firmware-qcom-vpu \
"
