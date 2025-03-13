RDEPENDS:${PN}:append = " \
    libdrm \
    wayland \
    wayland-protocols \
    weston \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xwayland', '', d)} \
    "

RDEPENDS:${PN}:append:qcom-custom-bsp = "\
    virtual/libgbm \
    virtual/libqbm \
    "

RDEPENDS:${PN}:remove:qcm6490-idp:qcom-base-bsp = "\
libdrm \
weston \
wayland \
wayland-protocols \
"

RDEPENDS:${PN}:remove:qcs6490-rb3gen2-core-kit:qcom-base-bsp = "\
libdrm \
weston \
wayland \
wayland-protocols \
"

RDEPENDS:${PN}:remove:qcs6490-rb3gen2-vision-kit:qcom-base-bsp = "\
libdrm \
weston \
wayland \
wayland-protocols \
"

RDEPENDS:${PN}:remove:qcs6490-rb3gen2-industrial-kit:qcom-base-bsp = "\
libdrm \
weston \
wayland \
wayland-protocols \
"
