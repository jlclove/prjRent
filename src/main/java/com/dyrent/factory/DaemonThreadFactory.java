package com.dyrent.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author "liuhui" 
 * @since 1.0.0
 * @createAt 2014-1-26 下午4:35:13
 * <p>
 *  daemon 线程工厂
 *  可以指定线程名与优先级
 * </p>
 * Copyright (c) 2014, Dooioo All Rights Reserved. 
 */
public class DaemonThreadFactory implements ThreadFactory {
	  private String name;
      private Integer priority;
		/**
		 * @param name 线程名
		 * @param priority
		 *     Thread.NORM_PRIORITY 等等
		 */
		public DaemonThreadFactory(String name ,int priority) {
		 this.name=name;
		 this.priority=priority;
		}
		public DaemonThreadFactory(String name) {
			this.name=name;
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setName(this.name);
			if (this.priority !=null) {
				t.setPriority(this.priority);
			}
			t.setDaemon(true);
			return t;
		}
}


