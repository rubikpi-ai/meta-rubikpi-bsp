DESCRIPTION = "Recipe to install dspso files on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100|qcs8300|qcs615"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${DSPSO}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "6439fd8b1756f9a6ed8b00f07f164b7951d8b1c533efba6f8b5a725bea307c4b"
SRC_URI[qcs9100.sha256sum] = "f57755ceadc1dfcf1e04908ad707ae42933d51ea145fdfe218f066b66bc8d3bb"
SRC_URI[qcs8300.sha256sum] = "d9cafe6ac570afba206721d93d63bed3077c6d8a99424515c1125f8a1d6a2979"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

DSPSO:qcm6490 = "QCM6490_dspso"
DSPSO:qcs9100 = "QCS9100_dspso"
DSPSO:qcs8300 = "QCS8300_dspso"
DSPSO:qcs615  = "QCS615_dspso"

DSPSO_PATH = "${WORKDIR}/git/${BUILD_ID}/${BIN_PATH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"

python do_install() {

    fw_file = d.getVar("DSPSO")
    fw_path = d.getVar("DSPSO_PATH")

    firmware_install(d, fw_file, fw_path)
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "/lib/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
