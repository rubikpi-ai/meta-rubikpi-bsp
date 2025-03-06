inherit autotools-brokensep pkgconfig

DESCRIPTION = "Bluetooth Fluoride Stack"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "zlib libchrome glib-2.0 property-vault qcom-audioroute libbsd"
RDEPENDS:${PN} = "property-vault"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV ?= "db943ed562f68d7f3a6d41dc79e0fdb731320e5e"

QCOM_BLUETOOTH_EXT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth_ext.git;protocol=https"
QCOM_BLUETOOTH_EXT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_EXT_SRCREV ?= "996bbb712e62c5c22489cd84fad1a93a91d65ddc"

QCOM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
QCOM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_SRCREV ?= "6656f553a4bbe3f22d7503d72de400c53ce54737"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"


SRCREV_FORMAT = "qcomsystembt_qcombluetoothext_qcombt_qcombluetooth"

SRCREV_qcomsystembt = "${QCOM_SYSTEM_BT_SRCREV}"
SRCREV_qcombluetoothext = "${QCOM_BLUETOOTH_EXT_SRCBRANCH}"
SRCREV_qcombt = "${QCOM_BT_SRCREV}"
SRCREV_qcombluetooth = "${QCOM_BLUETOOTH_SRCREV}"

SRC_URI = "${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};name=qcomsystembt;destsuffix=bluetooth/stack/system/bt \
           ${QCOM_BLUETOOTH_EXT_SRC};branch=${QCOM_BLUETOOTH_EXT_SRCBRANCH};name=qcombluetoothext;destsuffix=bluetooth/stack/bluetooth_ext \
           ${QCOM_BT_SRC};branch=${QCOM_BT_SRCBRANCH};name=qcombt;destsuffix=bluetooth/btapp \
           ${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};name=qcombluetooth;destsuffix=bluetooth/bt_audio"

S = "${WORKDIR}/bluetooth"
S_EXT = "${S}/stack/bluetooth_ext/system_bt_ext"

AUTOTOOLS_SCRIPT_PATH = "${S}/stack/system/bt"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'

PSEUDO_IGNORE_PATHS = "/dev/,${WORKDIR}/bluetooth,${WORKDIR}/pkgdata-sysroot,${TMPDIR}/sysroots-components"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}"
FILES:${PN} += "${sysconfdir}/bluetooth/*"
INSANE_SKIP:${PN} = "dev-so"

CPPFLAGS:append = " -DUSE_ANDROID_LOGGING -DUSE_LIBHW_AOSP"
CPPFLAGS:append = " -w -I${STAGING_INCDIR}"
CPPFLAGS:qcm6490 = " -DSUPPORT_ESL_AP"

CFLAGS:append = " -w -DNDEBUG  -I${STAGING_INCDIR}"

EXTRA_OECONF = " \
                --with-zlib \
                --with-common-includes="${S}/stack/system/bt" \
                --with-lib-path=${STAGING_LIBDIR} \
                --enable-static=yes \
                --with-chrome-includes="${STAGING_INCDIR}/chrome" \
                --disable-dependency-tracking \
               "
EXTRA_OECONF:append:qcm6490 = " --with-esl"

PACKAGE_ARCH = "${MACHINE_ARCH}"
do_install:append() {

	install -d ${D}${sysconfdir}/bluetooth/

	cd  ${D}/${libdir}/ && ln -s libbluetoothdefault.so bluetooth.default.so
	cd  ${D}/${libdir}/ && ln -s libaudioa2dpdefault.so audio.a2dp.default.so

	if [ -f ${S}/stack/system/bt/conf/auto_pair_devlist.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/auto_pair_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/bt_did.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/bt_did.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/bt_stack.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/bt_stack.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/interop_database.conf ]; then
		install -m 0660 ${S_EXT}/conf/interop_database.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S_EXT}/conf/bt_profile.conf ]; then
		install -m 0660 ${S_EXT}/conf/bt_profile.conf ${D}${sysconfdir}/bluetooth/
	fi

	if [ -f ${S}/stack/system/bt/conf/iot_devlist.conf ]; then
	   install -m 0660 ${S}/stack/system/bt/conf/iot_devlist.conf ${D}${sysconfdir}/bluetooth/
	fi

    install -d ${D}${includedir}/bluetooth
    install -m 0660 ${S}/stack/bluetooth_ext/system_bt_ext/include/bt_testapp.h ${D}${includedir}/fluoride/
    cd ${S}/stack/system/bt && find ./ -name '*.h'|xargs tar czf ${D}${includedir}/bluetooth/bluetooth.tgz
    tar zxvf ${D}${includedir}/bluetooth/bluetooth.tgz -C ${D}${includedir}/bluetooth && rm -f ${D}${includedir}/bluetooth/bluetooth.tgz
}
