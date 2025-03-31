DESCRIPTION = "Recipe to install firmware files at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "d8614693c8e19e9467294bf7b778a3e74dbbba1974ca1c312f82a9700b09478b"
SRC_URI[qcs9100.sha256sum] = "10dc83172334b0b71c1d5e828cd2a2d9e894c0ff360b23888ccd2c2d3047a310"
SRC_URI[qcs8300.sha256sum] = "a29456ae79cc2e90608609b61c6a30dd447f9c03af106bca94236a90762b5c66"
SRC_URI[qcs615.sha256sum] = "4d048449f28cc42bc2f38960e1421f716a54fa89de7a40fad4a296482fbd030e"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

HLOSFIRMWARE:qcm6490 = "QCM6490_fw"
HLOSFIRMWARE:qcs9100 = "QCS9100_fw"
HLOSFIRMWARE:qcs8300 = "QCS8300_fw"
HLOSFIRMWARE:qcs615  = "QCS615_fw"

HLOSFIRMWARE_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("HLOSFIRMWARE")
    fw_path = d.getVar("HLOSFIRMWARE_PATH")

    firmware_install(d, fw_file, fw_path)

    import os
    import shutil

    # Remove files in /usr/lib/dsp, as the same are provided by dspso fw.
    dsp_fwpath = os.path.join(d.getVar('D'), 'usr/lib/dsp')
    if os.path.exists(dsp_fwpath) and os.path.isdir(dsp_fwpath):
        shutil.rmtree(dsp_fwpath)

    # Move contents from /lib to /usr/lib if the usrmerge distro feature is enabled
    if bb.utils.contains('DISTRO_FEATURES', 'usrmerge', True, False, d):
        lib_path = os.path.join(d.getVar('D'), 'lib')
        firm_path = os.path.join(d.getVar('D'), 'lib/firmware')
        firm_path_usr = os.path.join(d.getVar('D'), 'usr/lib/firmware')
        if os.path.exists(firm_path_usr):
            shutil.rmtree(firm_path_usr)
        shutil.copytree(firm_path, firm_path_usr)
        shutil.rmtree(lib_path)
}

inherit deploy

do_deploy() {
    install -D -m644 ${D}${nonarch_base_libdir}/firmware/qcom/${MATCHED_MACHINE}/Ver_Info.txt ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "${nonarch_base_libdir}/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
