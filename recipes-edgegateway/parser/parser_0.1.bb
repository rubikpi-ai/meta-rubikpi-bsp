SUMMARY = "Sampled Value Parser for edgegateway"
DESCRIPTION = "Application to parse the IEC61850 data, detect anomalies, run protection Unit load with real time latencies and jitter"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

PR = "r0"

SRC_URI = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/edgegateway.git;branch=edgegateway.lnx.1.0;protocol=https"
SRCREV = "${AUTOREV}"
S="${WORKDIR}/git/parser"

CFLAGS += "\
    -I${S}/inc\
"

DEPENDS += "dpdk rapidjson paho-mqtt-cpp mosquitto sqlite"

inherit autotools-brokensep pkgconfig logging

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
       oe_runmake all
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/parser-shared ${D}${bindir}
}

PACKAGES = "${PN} ${PN}-dbg"
FILES:${PN} += "${bindir}/parser-shared"