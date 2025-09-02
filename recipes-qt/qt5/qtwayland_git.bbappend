FILESEXTRAPATHS:prepend := "${THISDIR}/qtwayland:"

SRC_URI += "\
	file://0001-Add-customized-set-position.patch \
"

DEPENDS += "virtual/libgles2"

PACKAGECONFIG:remove = "xcomposite-egl xcomposite-glx xcb"

do_install:append() {
	install -d ${D}${libdir}

	cd ${D}${libdir}
	ln -sf libGLESv2_adreno.so.2 libGLESv2_adreno.so
}

FILES:${PN} += "${libdir}/libGLESv2_adreno.so"