package waterpower.client

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import waterpower.common.block.watermill.EnumWatermill
import waterpower.common.init.WPBlocks

class CreativeTabWaterPower(label: String) : CreativeTabs(label) {

    override fun createIcon(): ItemStack {
        // 返回 MOD 的物品作为图标
        return WPBlocks.watermill.getItemStack(EnumWatermill.MK1)
    }
}
