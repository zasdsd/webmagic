package com.mj.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
	private Map<Object, Object> baseCache;
	private volatile static CacheManager instance;

	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized (CacheManager.class) {
				if (instance == null) {
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}

	// private static CacheManager instance;
	// private static Object lock = new Object();

	public CacheManager() {
		baseCache = new ConcurrentHashMap<Object, Object>();
	}

	// /**
	// * ������һ��CacheManager
	// *
	// * @return CacheManager����
	// */
	// public static CacheManager getInstance() {
	// if (instance == null) {
	// synchronized (lock) {
	// if (instance == null) {
	// instance = new CacheManager();
	// }
	// }
	// }
	// return instance;
	// }

	/**
	 * ����Ʊ������뻺����
	 * 
	 * @param news
	 *            ����Ʊ����
	 */
	public void putCache(String key, Object entity) {
		baseCache.put(key, entity);
	}

	/**
	 * ���ݹ�Ʊ���뽫�����еĹ�Ʊ�����Ƴ�
	 * 
	 * @param newsID
	 *            ����Ʊ����
	 */
	public void removeCache(String key) {
		if (baseCache.containsKey(key)) {
			baseCache.remove(key);
		}
	}

	/**
	 * ���ݹ�Ʊ����ӻ�������ȡ��Ӧ�Ĺ�Ʊ����
	 */
	public Object getCache(String key) {
		try {
			if (baseCache.containsKey(key))
				return baseCache.get(key);
			return null;
		} catch (Exception e) {
			// logger.debug("�ڻ�����ȡ������Ӧ��"+key+"��ԭ��:"+e.getMessage());
			return null;
		}
	}

	/**
	 * ɾ�����������еĶ���
	 */
	public void removeAll() {
		baseCache.clear();
	}
}
