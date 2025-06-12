inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "4636af537d564f89a868ab6c1821236c6941403274b9542d2a30fbf930e2785f"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "ff8f7e10313c9dab5524bf7abcf881fef892bfaee199a42f839c2f439eb27507"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "b98a15bcdcb243d6403ece6d52c472a96ee250e4148a8e6b8b0f248b6d01b0d3"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "14fdbf3fb91bd793ce52ed7b0a7977a826816d880f092dd2cdfea4bf8c635b7b"
QCS8300_RIDE_SX_SHA256SUM = "b7e2161abdc53eef5c380873b10fac0b076a9d4a814d2d5546e0456758e45e55"
QCS9100_RIDE_SX_SHA256SUM = "13eb437a6aa3abd7b3cf7f78a5748e0a49c6ed4083ead61542c7cb4ece2d202b"
QCS9075_RIDE_SX_SHA256SUM = "c0f955dd163bc695142f097bad32bd12828c076fa3818313b25a972a984212f4"
QCS9075_RB8_CORE_KIT_SHA256SUM = "d3f4ad14084a2878d4f750b1a4c4642a1eb80da5a582c05a796e5b6698253535"
QCS9075_IQ_9075_EVK_SHA256SUM = "a58904443140edfa1c7e0bc6fa88777ae1da1e9f251b50068746d66ea3523bc5"
QCS8275_IQ_8275_EVK_SHA256SUM = "76b0a3152120a0feb5830a78ba974e864b016a3a5ce02a0b4d06dea98d13fb0e"

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
