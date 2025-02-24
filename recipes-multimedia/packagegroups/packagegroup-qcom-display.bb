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

RDEPENDS:${PN}:append:qcom-custom-bsp = "\
    virtual/libgbm \
    "

RDEPENDS:${PN}:append:qcom-custom-bsp = " \
    kernel-module-qcom-touchdlkm \
    "

