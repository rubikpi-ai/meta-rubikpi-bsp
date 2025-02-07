SUMMARY = "QCOM Display package groups"

PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

LICENSE = "BSD-3-Clause & BSD-3-Clause-Clear"
LICENSE += "& Qualcomm-Technologies-Inc.-Proprietary"

PROVIDES = "${PACKAGES}"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    libcec \
    libdrm \
    wayland \
    wayland-protocols \
    weston \
    "

RDEPENDS:${PN}:append:qcm6490:qcom-custom-bsp = " \
    kernel-module-displaydlkm \
    qcom-display-hal-linux \
    qcom-displaydevicetree \
"

RDEPENDS:${PN}:append:qcom-custom-bsp = "\
    virtual/libgbm \
    "

RDEPENDS:${PN}:append:qcm6490 = " \
    qcom-display-extn-linux \
    qcom-display-color-linux \
    "
RDEPENDS:${PN}:append:qcom-custom-bsp = " \
    kernel-module-qcom-touchdlkm \
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

