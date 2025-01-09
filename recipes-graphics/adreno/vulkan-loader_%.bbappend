# We can not directly use PV here as it will be expanded too early.
# Thus temporary variable is used which gets substituted later.

FILESEXTRAPATHS:prepend:qcom-custom-bsp := "${THISDIR}/${BPN}-${package_version}:"
package_version = '${PV}'

SRC_URI:append:qcom-custom-bsp = " file://0001-Add-VkSharedPresentSurfaceCapabilitiesKHR_to_vkGetPhysicalDeviceSurfaceCapabilities2KHR.patch;patch=1"
RRECOMMENDS:${PN}:remove:qcom-custom-bsp = "mesa-vulkan-drivers"
