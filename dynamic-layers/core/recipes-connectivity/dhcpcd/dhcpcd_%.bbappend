FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append:qcom = " file://dhcpcd.conf "

do_install:append:qcom(){
    TARGET_CONF="${D}${sysconfdir}/dhcpcd.conf"
    if [ -f "${TARGET_CONF}" ]; then
        cat ${WORKDIR}/dhcpcd.conf >> "${TARGET_CONF}"
    else
        install -d ${D}${sysconfdir}
        install -m 0644 ${WORKDIR}/dhcpcd.conf "${TARGET_CONF}"
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/etc/tmpfiles.d
        install -m 0644 ${WORKDIR}/dhcpcd.conf ${D}/etc/tmpfiles.d/dhcpcd.conf

        rm -rf ${D}/${localstatedir}
    fi
}
