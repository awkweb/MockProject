package com.java.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

@Component
public class OrderManager {

	@Autowired
	OrderDao orderDao;

	public Order getOrderWithId(String orderId) {
		return orderDao.getOrderWithId(orderId);
	}

	public List<Order> getOrdersForBlock(Block block) {
		return orderDao.getOrdersForBlock(block);
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public Boolean removeOrderFromBlockWithOrderId(String orderId) {		
		return orderDao.removeOrderFromBlockWithOrderId(orderId);
	}

	public List<Order> getOpenOrdersforUser(User user) {
		return orderDao.getOpenOrdersForUser(user);
	}

	public List<Order> findOrdersWithIds(List<Integer> idList) {
		List<Order> orders = new ArrayList<Order>();
		for (Integer id : idList) {
			Order temp = getOrderWithId(id + "");
			orders.add(temp);
		}
		return orders;
	}

	public void updateOrder(Order order){
		orderDao.updateOrder(order);
	}

	public List<Block> getProposedBlocksWithOrders(List<Order> allorders, User user) {
		List<Block> pblocks = new ArrayList<Block>();
		boolean added = false;
		for (Order order : allorders) {
			
			for (Block pb : pblocks) {
				System.out.println(pb.getSide()+"_"+pb.getSymbol()+"_"+pb.getOrders().get(0).getOrdertype()+"_"+pb.getLimitPrice());

				if (pb.getSide().equals(order.getSide()) && pb.getSymbol().equals(order.getSecurity().getSymbol())) {
					if (pb.getOrders().get(0).getOrdertype()
							.equals(order.getOrdertype())) {
						pb.getOrders().add(order);
						pb.setTotalQty(pb.getTotalQty() + order.getTotalQty());

												if (pb.getSide().equals(("Buy"))
								&& pb.getOrders().get(0).getOrdertype()
										.equals("Limit")) {
							pb.setLimitPrice(getMinimumLimitPrice(pb
									.getOrders()));
						}

						if (pb.getSide().equals(("Buy"))
								&& pb.getOrders().get(0).getOrdertype()
										.equals("Stop")) {
							pb.setStopPrice(getMaxmiumStopPrice(pb.getOrders()));
						}

						if (pb.getSide().equals(("Sell"))
								&& pb.getOrders().get(0).getOrdertype()
										.equals("Limit")) {
							pb.setStopPrice(getMaximumLimitPrice(pb.getOrders()));
						}
						if (pb.getSide().equals(("Sell"))
								&& pb.getOrders().get(0).getOrdertype()
										.equals("Stop")) {
							pb.setStopPrice(getMinimumStopPrice(pb.getOrders()));
						}

						added = true;
					}
					break;
				}
			}
			if (!added) {
				Block newTemp = new Block(order.getSecurity().getSymbol(), order.getSide(),
						"Proposed", user, new ArrayList<Order>(), 0f, 0f);
				newTemp.getOrders().add(order);
				newTemp.setTotalQty(order.getTotalQty());
				if (order.getOrdertype().equals("Limit")) {
					newTemp.setLimitPrice(order.getLimitPrice());
				}
				if (order.getOrdertype().equals("Stop")) {
					newTemp.setStopPrice(order.getStopPrice());
				}
				pblocks.add(newTemp);
			System.out.println(newTemp.getSide()+"_"+newTemp.getSymbol());
			}
			added = false;

		}
		return pblocks;
	}

	public boolean canAddToBlock(List<Order> selected4Block) {
		Order temp1, temp2;
		if (selected4Block.size() > 1) {
			for (int i = 0; i < selected4Block.size() - 1; i++) {
				System.out.println(selected4Block.size());
				temp1 = selected4Block.get(i);
				temp2 = selected4Block.get(i + 1);

				System.out.println(temp1.getSecurity().getSymbol().toString() + " "
						+ temp2.getSecurity().getSymbol().toString());

				// check if order symbol are not the same
				if ((temp1.getSecurity().getSymbol().compareTo(temp2.getSecurity().getSymbol()) != 0)) {
					return false;
				} else {
					// check if side are not the same
					if ((temp1.getSide().compareTo(temp2.getSide()) != 0)) {
						return false;
					}

					if (temp1.getOrdertype().compareTo(temp2.getOrdertype()) != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public float getMinimumLimitPrice(List<Order> ordersList) {
		float min = 0;

		List<Float> minLimit = new ArrayList<Float>();
		for (Order o : ordersList) {
			minLimit.add(o.getLimitPrice());
		}

		min = Collections.min(minLimit);

		return min;
	}

	public float getMaximumLimitPrice(List<Order> ordersList) {

		float max = 0;

		List<Float> minLimit = new ArrayList<Float>();
		for (Order o : ordersList) {
			minLimit.add(o.getLimitPrice());
		}

		max = Collections.max(minLimit);

		return max;
	}

	public float getMinimumStopPrice(List<Order> ordersList) {
		float min = 0;

		List<Float> minStop = new ArrayList<Float>();
		for (Order o : ordersList) {
			minStop.add(o.getStopPrice());
		}

		min = Collections.min(minStop);

		return min;
	}

	public float getMaxmiumStopPrice(List<Order> ordersList) {
		float max = 0;

		List<Float> maxStop = new ArrayList<Float>();
		for (Order o : ordersList) {
			maxStop.add(o.getStopPrice());
		}
		max = Collections.max(maxStop);

		return max;
	}

	public Boolean updateOrderPriceForIdAndType(String id, String type, float price) {
		return orderDao.updateOrderPriceForIdAndType(id, type, price);
	}
	
	public List<Order> getEquitiesInPortfolio(Order order) {
		return orderDao.getEquitiesInPortfolio(order);
	}
	
	public long getTotalEquityOwned(Order order){
		return orderDao.getTotalEquityOwned(order);
	}

}
