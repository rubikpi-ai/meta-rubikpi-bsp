DESCRIPTION = "Recipe to install QUPv3 firmware on rootfs"

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "(qcs9100)"

# QUPv3 firmware is not available in linux-firmware git.
# Fetch from Qualcomm Software center to mitigate.
SRC_URI = "https://softwarecenter.qualcomm.com/download/software/chip/qualcomm_linux-spf-1-0/qualcomm-linux-spf-1-0_test_device_public/r1.0_00058.0/qcs9100-le-1-0/common/build/ufs/bin/QCS9100_fw.zip"
SRC_URI[sha256sum] = "c4978685d9898bff4dd8c75a7c9df157af2d7b2474f73020f6ae444cdcac550c"

inherit bin_package

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${WORKDIR}/QCS9100_fw/lib/firmware/qupv3fw.elf ${D}${nonarch_base_libdir}/firmware/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "arch"
