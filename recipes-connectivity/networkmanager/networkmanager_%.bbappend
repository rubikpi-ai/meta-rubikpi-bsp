FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom= "  file://0001-PENDING-data-disable-systemd-networkd-for-conflictio.patch \
"
