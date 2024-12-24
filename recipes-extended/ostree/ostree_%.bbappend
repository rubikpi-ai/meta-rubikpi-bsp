FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://0003-Add-support-for-directories-instead-of-symbolic-link.patch \
    file://0004-Add-support-for-systemd-boot-bootloader.patch \
    file://0001-Add-support-systemd-boot-Automatic-boot-assessment.patch \
"

# Static variant of ostree-prepare-root will work only when an initrd isn't used.
# As qcom bootflow uses initrd to mount rootfs, this configuration will cause boot up issues.
PACKAGECONFIG:remove:qcom = "static"
