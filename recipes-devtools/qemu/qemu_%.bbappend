PACKAGECONFIG:append:qcom = " virtfs vhost gtk+ libusb ftrace"
PACKAGECONFIG[ftrace] = "--enable-trace-backend=ftrace,,,"
PACKAGECONFIG[ust] = "--enable-trace-backends=ust,,lttng-ust,"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
            file://0001-virtio-iommu-Fix-the-domain_range-end.patch \
            "

