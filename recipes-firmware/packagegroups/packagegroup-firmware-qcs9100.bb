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
    linux-firmware-ath11k-wcn6855 \
    linux-firmware-qca-qca6698 \
    linux-firmware-qcom-adreno-a660 \
    linux-firmware-qcom-adreno-a663 \
    linux-firmware-qcom-sa8775p-adreno \
    linux-firmware-qcom-sa8775p-audio \
    linux-firmware-qcom-sa8775p-compute \
    linux-firmware-qcom-sa8775p-generalpurpose \
    linux-firmware-qcom-vpu \
"
