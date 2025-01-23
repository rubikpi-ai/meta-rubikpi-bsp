inherit qprebuilt pkgconfig systemd

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"
SUMMARY = "FastRPC user space libraries and daemons needed to offload to DSPs"

DEPENDS += "dspservices-headers qcom-libdmabufheap"

COMPATIBLE_MACHINE = "(qcom)"

DEFAULT_PREFERENCE = "-1"

PBT_ARCH = "${MACHINE_ARCH}"

QCM6490_IDP_SHA256SUM = "e6fe1c5f0609ea1fffca239b10c6659afa3a65a15580fa986e34a91a72211eff"
QCS6490_RB3GEN2_CORE_KIT_SHA256SUM = "2609648fd6d303dcde1e49bde6b37341783b0b03cf6e03d3e1f6c7488410f1da"
QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM = "69690b8d3bbf05feabf762555f20368963af4edb3e19ecedc7813ca859d1e1da"
QCS6490_RB3GEN2_VISION_KIT_SHA256SUM = "2298b13c5f38e21f116f0448442673a01f4230e266b7974642bda50a62388a50"
QCS8300_RIDE_SX_SHA256SUM = "93d58ab0599aa039a261ae0609f2c3500da8633a5602e1bb35f63f47ef005efd"
QCS9100_RIDE_SX_SHA256SUM = "b260f55a0004a7d565e1d7dc3af933bc8f587bdfca4293da671fccee2eff3209"
QCS9075_RIDE_SX_SHA256SUM = "a6af44206b57a5d7d8169c3cab0bc7cbd7835d950f315a2b1d09ede5171461b8"
QCS9075_RB8_CORE_KIT_SHA256SUM = "80f45b7056ebe3e165cf8cc0ded3c3c65d2d84452b4a468f166bd33263d81b05"

SRC_URI[qcm6490_idp.sha256sum] = "${QCM6490_IDP_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_core_kit.sha256sum] = "${QCS6490_RB3GEN2_CORE_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_industrial_kit.sha256sum] = "${QCS6490_RB3GEN2_INDUSTRIAL_KIT_SHA256SUM}"
SRC_URI[qcs6490_rb3gen2_vision_kit.sha256sum] = "${QCS6490_RB3GEN2_VISION_KIT_SHA256SUM}"
SRC_URI[qcs8300_ride_sx.sha256sum] = "${QCS8300_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9100_ride_sx.sha256sum] = "${QCS9100_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_ride_sx.sha256sum] = "${QCS9075_RIDE_SX_SHA256SUM}"
SRC_URI[qcs9075_rb8_core_kit.sha256sum] = "${QCS9075_RB8_CORE_KIT_SHA256SUM}"


SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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
