inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "qcom-libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"


QCM6490_SHA256SUM = "04246834c77ed0e2123ded78bee5238d2d269d527d6248aa5ad794cea19457aa"
QCS9100_SHA256SUM = "2c601ef35893643fc23dd4d495263b7a8bf102303dbb90ce9a18d0c93dba9de7"
QCS8300_SHA256SUM = "a8ce10654b8ff9d958e33eca4f7205cb594eda9be27e506cc407be2593884dd2"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so* ${libdir}/libfastcvdsp_stub.so*"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-dsp += "${libdir}/dsp/cdsp/cv/*"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN}-cpu += "dev-so"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
