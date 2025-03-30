inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "c38e69589f8e2d0913abcde067eabfdf1be98fe480f7f1722ab82a52aed2142d"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "cc776dc6c2bf6a6d7f0489885a02f947b54bce8fb1a0c3bafef52c463b347e88"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "a6526f0f15bea17ea87c86ddb559503274ed0ba221e0539bea58a591836418ae"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "d5ef2654afdd16de279e7a050ea2472d4f5bb724475fd21f5e60d27351cd2969"
QCS8300_RIDE_SX_SHA256SUM = "0a5c789f0169893b2f2cb1abe180dc0f419ac464c25a4f05d7ac72189f67b6ad"
QCS9100_RIDE_SX_SHA256SUM = "6c0a8f23554a508c499286660301623229a4aae103dd45e4c07097bca26f83ad"
QCS9075_RIDE_SX_SHA256SUM = "fe8885bac9190e0af661ae3e458e7d4dd906e4376de04af4183611de0fe5049a"
QCS9075_RB8_CORE_KIT_SHA256SUM = "1ffbddeaba1f29a5dd8faec60c83e9948386de662b340a81c2d22b7b729dcdf5"
QCS9075_IQ_9075_EVK_SHA256SUM = "f3e9620871f5785807b56ca75c34fd54a9576a4a528bbe7205e3ca2a9662c466"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"
SRC_URI[qcs9075_iq_9075_evk.sha256sum] = "${QCS9075_IQ_9075_EVK_SHA256SUM}"


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
