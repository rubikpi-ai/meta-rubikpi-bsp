DESCRIPTION = "Recipe to install NHLOS images in DEPLOY_DIR"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

PROVIDES += "virtual/bootbins"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${BOOTBINARIES}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "08c0798f1ab9f380c94b54141847c7b365c87f2a072a2461779cf282809aeeb4"
SRC_URI[qcs9100.sha256sum] = "bd024ffe419f13b19907b285d0369bf9dfdf77b7e95052b9e4869957ddcaf07f"
SRC_URI[qcs8300.sha256sum] = "224e3d59239efc4e64baec98db5c4df0f5ccd166b75226455533996dac0debae"
SRC_URI[qcs615.sha256sum]  = "977ac0bf59ea751787468974b329ef6f755ddfc13d74f6fbb951726aaf7894e2"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

BOOTBINARIES:qcm6490 = "QCM6490_bootbinaries"
BOOTBINARIES:qcs9100 = "QCS9100_bootbinaries"
BOOTBINARIES:qcs8300 = "QCS8300_bootbinaries"
BOOTBINARIES:qcs615  = "QCS615_bootbinaries"

BOOTBINARIES_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("BOOTBINARIES")
    fw_path = d.getVar("BOOTBINARIES_PATH")

    firmware_install(d, fw_file, fw_path)

    # Remove partition xmls.
    for item in os.listdir(d.getVar('D')):
        name, ext = os.path.splitext(item)
        if name.startswith('partition') and ext == '.xml':
            os.remove(os.path.join(d.getVar('D'), item))

}

inherit deploy

do_deploy() {
    find "${D}" -name '*.bin' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -name '*.elf' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -name '*.fv' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -name '*.mbn' -exec install -m 0644 {} ${DEPLOYDIR} \;
    find "${D}" -name '*.melf' -exec install -m 0644 {} ${DEPLOYDIR} \;
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/*.elf /*.mbn /*.bin /*.fv */.melf"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "arch"
