/**
 * Copyright (c) Huang Yuhui, 2017
 *
 * "WaterPower" is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package waterpower.common.block.attachment

import buildcraft.api.mj.MjAPI
import buildcraft.api.mj.IMjReceiver
import buildcraft.api.mj.IMjConnector
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import waterpower.common.Energy
import waterpower.common.block.tile.TileEntityBase

class AttachmentMJ(master: TileEntityBase, val energyStorage: EnergyStorage) : TileEntityAttachment(master) {
    override fun getName() = "mj"

    lateinit var mjConnector: Any

    override fun onLoaded() {
        super.onLoaded()

        // BuildCraft 8.0.0 使用Capability系统，不再需要单独的mjConnector
        // MJ能源传输现在通过MjAPI.ENERGY Capability处理
    }

    override fun onTick() {
        super.onTick()

        runIgnoringThrowables {
            for (facing in EnumFacing.VALUES)
                sendPower(facing)
        }
    }

    fun getReceiverToPower(tile: TileEntity?, side: EnumFacing): IMjReceiver? {
        if (tile == null) {
            return null
        } else {
            // BuildCraft 8.0.0 使用MjAPI.CAP_RECEIVER Capability
            val energyHandler = tile.getCapability(MjAPI.CAP_RECEIVER, side.opposite)
            return energyHandler
        }
    }

    private fun getTileBuffer(facing: EnumFacing)
            = master.world.getTileEntity(master.pos.offset(facing))

    private fun getPowerToExtract(facing: EnumFacing, doExtract: Boolean): Long {
        val tile = this.getTileBuffer(facing)
        if (tile == null) {
            return 0L
        } else {
            val receiver = this.getReceiverToPower(tile, facing)
            return if (receiver == null) 0L
            else {
                // BuildCraft 8.0.0 中能源需求通过不同方式获取
                val requested = receiver.powerRequested // 获取需求的能量
                energyStorage.extractEnergy(Energy.MJ2EU(requested.toDouble()), doExtract).toLong()
            }
        }
    }

    private fun sendPower(facing: EnumFacing) {
        val tile = this.getTileBuffer(facing)
        if (tile != null) {
            val receiver = this.getReceiverToPower(tile, facing)
            if (receiver != null) {
                // 获取可发送的能量
                val availableEnergy = energyStorage.getEnergyStored()
                if (availableEnergy > 0) {
                    // 转换为MJ (1 EU = 0.25 MJ，所以需要足够的EU)
                    val mjToSend = Energy.EU2MJ(availableEnergy.toDouble()).toLong()
                    if (mjToSend > 0) {
                        // BuildCraft 8.0.0 能源传输
                        val accepted = receiver.receivePower(mjToSend, false)
                        if (accepted > 0) {
                            val euConsumed = Energy.MJ2EU(accepted.toDouble())
                            energyStorage.extractEnergy(euConsumed, true)
                        }
                    }
                }
            }
        }
    }

    fun runIgnoringThrowables(x: () -> Unit) {
        try {
            x()
        } catch(ignore: Throwable) {
        }
    }
}