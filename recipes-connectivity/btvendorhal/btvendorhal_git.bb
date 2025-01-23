inherit autotools-brokensep

DESCRIPTION = "hardware btvendorhal headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=89aea4e17d99a7cacdbeed46a0096b10"

QCOM_BLUETOOTH_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/bluetooth.git;protocol=https"
QCOM_BLUETOOTH_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BLUETOOTH_SRCREV ?= "1710c237b493454dc93f41de09b50cd8d109f970"

QCOM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/qcom-opensource/bt.git;protocol=https"
QCOM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_BT_SRCREV ?= "7a71dcfa0b04ee88fc03af310d947362f7336fa1"

QCOM_SYSTEM_BT_SRC ?= "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/system/bt.git;protocol=https"
QCOM_SYSTEM_BT_SRCBRANCH ?= "bt-performant.qclinux.1.0.r1-rel"
QCOM_SYSTEM_BT_SRCREV ?= "c96740fc959230259215e77b31fac499b8aa3e1f"

SRCREV_FORMAT = "bluetooth_qcombt_systembt"

SRCREV_bluetooth = "${QCOM_BLUETOOTH_SRCREV}"
SRCREV_qcombt = "${QCOM_BT_SRCREV}"
SRCREV_systembt = "${QCOM_SYSTEM_BT_SRCREV}"

SRC_URI = "${QCOM_BLUETOOTH_SRC};branch=${QCOM_BLUETOOTH_SRCBRANCH};name=bluetooth;destsuffix=bluetooth/bt_audio \
           ${QCOM_BT_SRC};branch=${QCOM_BT_SRCBRANCH};name=qcombt;destsuffix=bluetooth/btapp \
           ${QCOM_SYSTEM_BT_SRC};branch=${QCOM_SYSTEM_BT_SRCBRANCH};name=systembt;destsuffix=bluetooth/stack/system/bt"

S = "${WORKDIR}/bluetooth"

AUTOTOOLS_SCRIPT_PATH = "${S}/bt_audio"

EXTRA_OEMAKE += 'BT_SOURCE=${S}'
