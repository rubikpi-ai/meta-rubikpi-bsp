inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "0860a27e34c38bf57d71c098247a55076a9dc1da1e789bff8be8dcf01ee282f8"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "8e86692527030267e708b5d4772787066e2c8a1d815da1608763e8e8d83462f3"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "c69ae8217a237d9d0767c7a9dfa71974ca8dd90ef0d3484a324babd369236013"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "7cbd7bd4ed4198a1adfdd2def61efbe02d114c1aa76fd407469aeb7591461b84"
QCS8300_RIDE_SX_SHA256SUM = "a7c670f0bee7320e0db839645592db1a68d3ce701c36ed4c4da7069721304d90"
QCS9100_RIDE_SX_SHA256SUM = "96afe2f8695ddb2e87654ee544bab85ff8c52b0a735fd0445b0fa8159cd21bd0"
QCS9075_RIDE_SX_SHA256SUM = "08defbbf95ba693823b21e3fe88508a1dd28ccc3b415bf426951f782e588cb3d"
QCS9075_RB8_CORE_KIT_SHA256SUM = "d3f4ad14084a2878d4f750b1a4c4642a1eb80da5a582c05a796e5b6698253535"
QCS9075_IQ_9075_EVK_SHA256SUM = "54c6465593491c63275545f15b863a6893694bf93cda3f5ad9ccb1714d8026d3"
QCS8275_IQ_8275_EVK_SHA256SUM = "6771eb796e8b4ee4651f47763e56913092ffd30326e98ca8d9506e1f27d8dfb6"
QCS8275_IQ_8275_EVK_IFP_SHA256SUM = "4e988a01c9b29159818dc62425c75bfb3eb33eae91f2497399c3a4e3f31a32d0"
QCS8275_IQ_8275_EVK_PRO_SKU_SHA256SUM = "81c0aa330167aa08b36cb42b92067e332564f21b30cd49927d9d4290927d4d83"
QCS9075_IQ_9075_EVK_IFP_SHA256SUM = "42c7738118417e8e418bfbe07687fa97d3f7f67859322b065bcd30fdb698ab4b"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"
SRC_URI[qcs9075_iq_9075_evk.sha256sum] = "${QCS9075_IQ_9075_EVK_SHA256SUM}"
SRC_URI[qcs8275_iq_8275_evk.sha256sum] = "${QCS8275_IQ_8275_EVK_SHA256SUM}"
SRC_URI[qcs8275_iq_8275_evk_ifp.sha256sum] = "${QCS8275_IQ_8275_EVK_IFP_SHA256SUM}"
SRC_URI[qcs8275_iq_8275_evk_pro_sku.sha256sum] = "${QCS8275_IQ_8275_EVK_PRO_SKU_SHA256SUM}"
SRC_URI[qcs9075_iq_9075_evk_ifp.sha256sum] = "${QCS9075_IQ_9075_EVK_IFP_SHA256SUM}"



SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

# Install systemd unit file at right location
relocate_systemd_unit_files () {
    if [ -d "${D}/lib/systemd" ]; then
        install -d ${D}/usr/lib/
        mv ${D}/lib/systemd  ${D}/usr/lib/systemd
    fi
}
do_install[postfuncs] += "relocate_systemd_unit_files"

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"

PACKAGE_ARCH    ?= "${MACHINE_ARCH}"

INSANE_SKIP:${PN} += "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "dev-so"

SYSTEMD_SERVICE:${PN}:append:qcom = " adsprpcd.service cdsprpcd.service"
SYSTEMD_SERVICE:${PN}:append:qcs9100 = " cdsp1rpcd.service"
