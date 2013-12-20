package com.demo.test;

import java.util.ArrayList;
import java.util.List;


public class MaximumProfit {
	private class Solution{
		public int buy=-1;
		public int sell=-1;		
	}
	public List<Solution> maxProfit(int[] prices){
		List<Solution> sol = new ArrayList<Solution>();
		if(prices.length <= 1)
			return null;
		Solution s = new Solution();
		s.buy = 0;
		for(int i=1;i<prices.length;i++){
			if(prices[i]>=prices[i-1]){
				s.sell=i;
			}else{
				if(s.sell > s.buy){
					sol.add(s);
					s = new Solution();
					s.buy=i;
				}else{
					s.buy = i;
				}				
			}
		}
		if(s.sell > s.buy)
			sol.add(s);
		return sol;
	}
	public static void main(String[] s){
		int[] prices = {100, 180, 260, 310, 40, 535, 695};
		List<MaximumProfit.Solution> sol = new MaximumProfit().maxProfit(prices);
		for(int i=0;i<sol.size();i++){
			System.out.println("Buying point = "+sol.get(i).buy+", Selling point = "+sol.get(i).sell);
		}
	}
}
