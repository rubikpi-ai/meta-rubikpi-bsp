LICENSE_FLAGS = ""
LICENSE = "MIT"

FILESEXTRAPATHS:prepend := "${THISDIR}/weston-launch:"

SRC_URI:append:qcom = "   file://weston.png \
              file://weston.desktop \
              file://xwayland.weston-start \
              file://systemd-notify.weston-start"

SRC_URI:append:qcom-custom-bsp = "   \
              file://0001-weston-Add-stack-protector-flag.patch"

SRC_URI:append:qcm6490:qcom-custom-bsp = "  \
              file://0001-weston-add-sdm-option.patch \
              file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
              "

SRC_URI:append:qcs9100 = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            "

SRC_URI:append:qcs9100:qcom-base-bsp = " file://0001-weston-avoid-duplicate-format.patch"

SRC_URI:append:qcs8300 = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            "

SRC_URI:append:qcs8300:qcom-base-bsp = " file://0001-weston-avoid-duplicate-format.patch"

SRC_URI:append:qcs615  = "  file://0001-weston-add-sdm-option.patch \
                            file://0001-weston-avoid-duplicate-format.patch \
                            file://0001-drm-backend-power-off-during-hotplug-disconnect.patch \
                            "

DEPENDS:append:qcom-custom-bsp = " property-vault qcom-libdmabufheap"

EXTRA_OEMESON += "-Dbackend-default=auto -Dbackend-rdp=false"

RRECOMMENDS:${PN} = "weston-launch liberation-fonts"

REQUIRED_DISTRO_FEATURES:remove:qcom = "opengl"

# select compositor, enable simple and demo clients and enable EGL
PACKAGECONFIG:qcom = " \
                 egl \
                 clients \
                 shell-desktop \
                 screenshare \
                 shell-fullscreen \
                 shell-ivi \
                 image-jpeg \
                 "

PACKAGECONFIG:append:qcm6490 = "kms"
PACKAGECONFIG:append:qcs9100 = "kms"
PACKAGECONFIG:append:qcs8300 = "kms"
PACKAGECONFIG:append:qcs615  = "kms"

# Weston on SDM
PACKAGECONFIG[sdm] = "-Dbackend-sdm=true,-Dbackend-sdm=false"
# Weston with disabling display power key
PACKAGECONFIG[disablepowerkey] = "-Ddisable-power-key=true,-Ddisable-power-key=false"

# Enable when sdm downstream is needed
#LDFLAGS:append:qcm6490  = " -ldrmutils -ldisplaydebug -lglib-2.0 -ldmabufheap"

# #meson script's CPP flags
#CXXFLAGS:append:qcm6490  = " -I${STAGING_INCDIR}/sdm"
# CXXFLAGS:append:qcm6490  = " -I${STAGING_INCDIR}/display/display"

#do_install:append:qcm6490() {
#    install -m 0644 ${WORKDIR}/weston.ini -D ${D}${sysconfdir}/xdg/weston/weston.ini
#}

FILES:${PN} += "${bindir}/*"
FILES:${PN} += " ${libdir}/*.so"
FILES:${PN} += "${sysconfdir}/xdg/weston/weston.ini"
