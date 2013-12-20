package com.demo.test;

public class LinearJars {
	
	class Result{
		protected int fume;
		protected int color;
		public Result(int f,int c){
			fume = f;
			color = c;
		}
	}
	
	public Result mix(Result r1,Result r2){
		return new Result(r1.fume+r2.fume+r1.color*r2.color,(r1.color+r2.color)%100);
	}
	public Result max(Result r1,Result r2){
		if (r1.fume > r2.fume)
			return r1;
		else
			return r2;
	}
	public Result mixJars(int[] jars,int st,int en) throws InterruptedException{
		if(st < 0 || en >= jars.length || st > en)
			return new Result(0,0);
		if(st==en)
			return new Result(0,jars[st]);
		int mid = st + (en - st)/2;
		Result lr = mixJars(jars,st,mid);
		Result rr = mixJars(jars,mid+1,en);
		Result lr_1 = mixJars(jars,st,mid-1);
		Result rr_1 = mixJars(jars,mid+2,en);
		Result m = new Result(jars[mid]*jars[mid+1],(jars[mid]+jars[mid+1])%100);
		Result m_1 = new Result(lr_1.fume + m.fume + lr_1.color*m.color,(lr_1.color+m.color)%100);
		Result m_2 = new Result(rr_1.fume + m.fume + rr_1.color*m.color,(rr_1.color+m.color)%100);
		return max(max(mix(lr,rr),mix(m_1,rr_1)),mix(lr_1,m_2));
	}
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LinearJars lj = new LinearJars();
		int[] jars = {1,5,3};
		Result r = lj.mixJars(jars, 0, jars.length-1);
		System.out.println("Max Fume = "+r.fume + ", Color = "+r.color);

	}

}
