FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}-${PV}:"
SECTION = "kernel"

SUMMARY = "Linux kernel for QCOM devices"
DESCRIPTION = "Recipe to build Linux kernel from 6.6 LTS branch"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'sota', 'sota', '', d)}

COMPATIBLE_MACHINE = "(qcom)"

SRC_URI = " git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=linux-6.6.y \
            file://qcom.cfg \
            file://vm-configs/qcom_vm.cfg \
            file://qcom_debug.cfg \
            "
# Apply qcom patches
require ${BPN}-${PV}/configs.inc
require ${BPN}-${PV}/devicetree.inc
require ${BPN}-${PV}/drivers.inc
require ${BPN}-${PV}/dt-bindings.inc
require ${BPN}-${PV}/tools.inc
require ${BPN}-${PV}/documentation.inc

KERNEL_CONFIG_FRAGMENTS:append = " ${WORKDIR}/qcom.cfg"
KERNEL_CONFIG_FRAGMENTS:append = " ${@oe.utils.vartrue('DEBUG_BUILD', '${WORKDIR}/qcom_debug.cfg', '', d)}"

S = "${WORKDIR}/git"

# 6.6.78
SRCREV = "4407146cf3fcfe8883f6123b89f861a11cc4e4df"
PV = "6.6+git${SRCPV}"

KERNEL_CONFIG ??= "defconfig"

# List of kernel modules that will be auto-loaded for Qualcomm platforms.

# Coresight and stm modules for QDSS functions
KERNEL_MODULE_AUTOLOAD += "coresight coresight-tmc coresight-funnel"
KERNEL_MODULE_AUTOLOAD += "coresight-replicator coresight-etm4x coresight-stm"
KERNEL_MODULE_AUTOLOAD += "coresight-cti coresight-tpdm coresight-tpda coresight-dummy"
KERNEL_MODULE_AUTOLOAD += "coresight-remote-etm coresight-tgu"
KERNEL_MODULE_AUTOLOAD += "stm_core stm_p_ost stm_p_basic stm_console stm_heartbeat stm_ftrace"

kernel_conf_variable() {
    sed -e "/CONFIG_$1[ =]/d;" -i ${B}/.config
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}

do_configure:prepend() {
    if [ ! -f "${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}" ]; then
        bbfatal "KERNEL_CONFIG '${KERNEL_CONFIG}' was specified, but not present in the source tree"
    else
        cp '${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}' '${B}/.config'
    fi

    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`git --git-dir=${S}/.git  rev-parse --verify --short HEAD 2> /dev/null`
        printf "%s%s" +g $head > ${B}/.scmversion
    fi

    if [ ! -z "${LINUX_VERSION_EXTENSION}" ]; then
        echo "# Global settings from linux recipe" >> ${B}/.config
        echo "CONFIG_LOCALVERSION="\"${LINUX_VERSION_EXTENSION}\" >> ${B}/.config
    fi

    # Check for kernel config fragments.  The assumption is that the config
    # fragment will be specified with the absolute path.  For example:
    #   * ${WORKDIR}/config1.cfg
    #   * ${S}/config2.cfg
    # Iterate through the list of configs and make sure that you can find
    # each one.  If not then error out.
    # NOTE: If you want to override a configuration that is kept in the kernel
    #       with one from the OE meta data then you should make sure that the
    #       OE meta data version (i.e. ${WORKDIR}/config1.cfg) is listed
    #       after the in kernel configuration fragment.
    # Check if any config fragments are specified.
    if [ ! -z "${KERNEL_CONFIG_FRAGMENTS}" ]
    then
        for f in ${KERNEL_CONFIG_FRAGMENTS}
        do
            # Check if the config fragment was copied into the WORKDIR from
            # the OE meta data
            if [ ! -e "$f" ]
            then
                echo "Could not find kernel config fragment $f"
                exit 1
            fi
        done

        # Now that all the fragments are located merge them.
        ( cd ${WORKDIR} && ${S}/scripts/kconfig/merge_config.sh -m -r -O ${B} ${B}/.config ${KERNEL_CONFIG_FRAGMENTS} 1>&2 )
    fi

}

do_configure:append() {
    oe_runmake -C ${S} O=${B} savedefconfig && cp ${B}/defconfig ${WORKDIR}/defconfig.saved
}
