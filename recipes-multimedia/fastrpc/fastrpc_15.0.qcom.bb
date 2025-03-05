inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "562eb7b31116aa9bbee906963ce5017ad1dc9029f3a30bd491a2ad3116777739"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "b5eb16fe81c4adc4da1e36b3b2506fee44ba52fe35507a6782e32a9c1f27375e"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "3563afbd02a068ffbbfd25d3fc0397cc291e0e3880a6eca1c0df1b95037697b0"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "a529c7d38c12b5be965d11eea8e6a44a086e571df22931d3e398a2534249d57a"
QCS8300_RIDE_SX_SHA256SUM = "40491b243bbd7ddb655ae86899d9d682436c5b8c17e1fc9c410365fd63d8b454"
QCS9100_RIDE_SX_SHA256SUM = "5c90509c0790a5b0cfceae63321160946beff5e9bd7dbee0a725e429bde081c5"
QCS9075_RIDE_SX_SHA256SUM = "dba3a30689ae7590e07faa45d98be60122352d2cd94b8a4970074b0ee431dc72"
QCS9075_RB8_CORE_KIT_SHA256SUM = "48dc92dd34342a4406269c338256f9e3379f8bf280efb6bfb078e71428530356"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"


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
