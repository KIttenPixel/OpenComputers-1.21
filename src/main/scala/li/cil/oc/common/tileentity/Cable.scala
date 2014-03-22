package li.cil.oc.common.tileentity

import li.cil.oc.api.network.{Analyzable, Visibility}
import li.cil.oc.server.TickHandler
import li.cil.oc.{api, common}
import net.minecraft.entity.player.EntityPlayer

class Cable extends traits.Environment with Analyzable {
  val node = api.Network.newNode(this, Visibility.None).create()

  def onAnalyze(player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float) = null

  override def canUpdate = false

  override def validate() {
    super.validate()
    TickHandler.schedule(this)
  }

  override def getRenderBoundingBox = common.block.Cable.bounds(world, x, y, z).offset(x, y, z)

  // ----------------------------------------------------------------------- //
  // Immibis Microblock support.

  val ImmibisMicroblocks_TransformableTileEntityMarker = null

  def ImmibisMicroblocks_isSideOpen(side: Int) = true

  def ImmibisMicroblocks_onMicroblocksChanged() {
    api.Network.joinOrCreateNetwork(this)
  }
}
