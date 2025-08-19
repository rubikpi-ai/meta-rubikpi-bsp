FILESEXTRAPATHS:prepend := "${THISDIR}/qtwayland:"

SRC_URI += "\
	file://0001-Add-customized-set-position.patch \
"

PACKAGECONFIG:remove = "xcomposite-egl xcomposite-glx xcb"
