package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.BlockDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

@Component
public class BlockManager {

	@Autowired
	BlockDao blockDao;

	@Autowired
	OrderManager orderManager;

	public Block getBlockWithId(String blockId) {
		return blockDao.getBlockWithId(blockId);
	}

	public List<Block> getBlocksForUserWithStatus(User user, String status) {
		return blockDao.getBlocksForUserWithStatus(user, status);
	}

	public List<Block> getBlocksForUserExlcudingStatus(User user, String status) {
		return blockDao.getBlocksForUserExlcudingStatus(user, status);
	}

	public void saveBlock(Block block) {
		blockDao.saveBlock(block);
	}

	public Boolean setStatusForBlockWithBlockId(String blockId, String status) {
		return blockDao.setStatusForBlockWithBlockId(blockId, status);
	}

	public Boolean addQtyForBlockWithBlockId(String blockId, Integer qty) {
		return blockDao.addQtyForBlockWithBlockId(blockId, qty);
	}

	public Boolean setQtyForBlockWithBlockId(String blockId, Integer qty) {
		return blockDao.setQtyForBlockWithBlockId(blockId, qty);
	}

	public List<Block> getBlocksForOrder(Order order) {
		return blockDao.getBlocksForOrder(order);
	}

	public void updateBlock(Block selectedBlock) {
		blockDao.updateBlock(selectedBlock);

	}

	public Block createBlockForType(List<Order> order) {
		Order o = order.get(0);
		Block b = null;

		switch (o.getOrdertype()) {
		case "Market":
			b = new Block(o.getSecurity().getSymbol(), o.getSide(), "new", o.getUser2(), order, 0f, 0f);
			break;
		case "Limit":
			b = new Block(o.getSecurity().getSymbol(), o.getSide(), "new", o.getUser2(), order, getTypePrice(order), 0f);
			break;
		case "Stop":
			b = new Block(o.getSecurity().getSymbol(), o.getSide(), "new", o.getUser2(), order, 0f, getTypePrice(order));
			break;

		}

		return b;
	}

	public Float getTypePrice(List<Order> orders) {
		Order o = orders.get(0);

		if (o.getSide().equals("Buy") && o.getOrdertype().equals("Limit"))
			return orderManager.getMinimumLimitPrice(orders);

		if (o.getSide().equals("Buy") && o.getOrdertype().equals("Stop"))
			return orderManager.getMaxmiumStopPrice(orders);

		if (o.getSide().equals("Sell") && o.getOrdertype().equals("Limit"))
			return orderManager.getMaximumLimitPrice(orders);

		if (o.getSide().equals("Sell") && o.getOrdertype().equals("Stop"))
			return orderManager.getMinimumStopPrice(orders);

		return 0f;

	}

}
