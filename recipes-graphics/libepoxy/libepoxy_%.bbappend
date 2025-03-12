PACKAGECONFIG[x11] = "-Dglx=yes, -Dglx=no -Dx11=false, virtual/libx11"
VIRTUAL_LIBGL = ""
python () {
    mach_overrides = d.getVar('MACHINEOVERRIDES').split(":")
    if ('qcom-custom-bsp' not in mach_overrides):
        d.setVar('VIRTUAL_LIBGL', "virtual/libgl")
}
PACKAGECONFIG[x11] += " ${VIRTUAL_LIBGL}"
