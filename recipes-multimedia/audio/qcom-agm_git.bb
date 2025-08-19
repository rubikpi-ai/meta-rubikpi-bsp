SUMMARY = "AGM for AROSP"

LICENSE = "BSD-3-Clause & LGPL-2.1-only"
LIC_FILES_CHKSUM += "file://service/src/agm.c;beginline=30;endline=31;md5=c901025e24b8cbc3b2ec2714b0571261 \
                     file://service/inc/public/agm/agm_api.h;beginline=31;endline=32;md5=c901025e24b8cbc3b2ec2714b0571261"

inherit autotools pkgconfig

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/agm.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "c34bbd51fb9797ce592d74e22c25af70c4746415"

FILESPATH =+ "${THISDIR}/agm:"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/agm \
    file://0002_Modify_the_backend_conf_xml_file.patch \
    file://0003_Change_the_capture_format_of_ES8316_from_1ch_to_2ch.patch \
    file://0004_Change_the_HDMI_OUT_AUDIO_format_from_16_to_32bit.patch \
    file://0005_Enable_the_third_i2s.patch \
    file://0006_Enable_BTHS_record.patch \
    file://0007_Add_agmhostless_tool_to_Linux.patch \
"

S = "${WORKDIR}/audio/opensource/agm"

DEPENDS = "glib-2.0 qcom-kvh2xml tinyalsa tinycompress qcom-args expat"
EXTRA_OECONF += "--with-glib --with-syslog --with-agm-no-ipc"
SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
