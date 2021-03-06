package org.minedustry.utilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT>
{
	public CustomEnergyStorage(int capacity, int maxTransfer)
	{
		super(capacity, maxTransfer);
	}
	
	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract)
	{
		super(capacity, maxReceive, maxExtract);
	}

	public void setEnergy(int energy)
	{
		this.energy = energy;
	}
	
	private void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	private int getExtractCapacity()
	{
		return maxExtract;
	}
	
	private int getReceiveCapacity()
	{
		return maxReceive;
	}

	public void addEnergy(int energy)
	{
		this.energy += energy;
		if (this.energy > this.capacity)
		{
			this.energy = this.capacity;
		}
	}

	public void consumeEnergy(int energy)
	{
		this.energy -= energy;
		if (this.energy < 0)
		{
			this.energy = 0;
		}
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putInt(NBTs.ENERGY, getEnergyStored());
		tag.putInt(NBTs.MAX_ENERGY, getMaxEnergyStored());
		tag.putInt(NBTs.MAX_EXTRACT, getExtractCapacity());
		tag.putInt(NBTs.MAX_INSERT, getReceiveCapacity());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		setEnergy(nbt.getInt(NBTs.ENERGY));
		setCapacity(nbt.getInt(NBTs.MAX_ENERGY));
		this.maxExtract = nbt.getInt(NBTs.MAX_EXTRACT);
		this.maxReceive = nbt.getInt(NBTs.MAX_INSERT);		
	}
}
