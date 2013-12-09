package 高橋健太.JPL.ch17.ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ref_Org_ResourceManager {

	final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	final Thread reaper;
	boolean shutdown = false;

	public ref_Org_ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		reaper = new ReaperThread();
		reaper.start();

		//リソースの初期化
	}


	public synchronized void shutdown() {
		System.out.println("debug:called ResourceManager.shutdown()");
		if(!shutdown) {
			shutdown = true;
			reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if(shutdown)
			throw new IllegalStateException();
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	public class ReaperThread extends Thread {

		@Override
		public void run() {
			// 割り込まれるまで実行
			while(true) {
				System.out.println("debug:ReaperThread running");
				Reference<?> ref;
				try {
					ref = queue.remove();
					Resource res = null;
					synchronized (ref_Org_ResourceManager.this) {
						res = refs.get(ref);
						refs.remove(ref);
					}
					res.release();
					ref.clear();
				} catch (InterruptedException e) {
					break; //すべて終了
				}
			}
		}
	}

	private static class ResourceImpl implements Resource {

		int keyHash;
		String resource;
		boolean needsRelease = false;

		ResourceImpl(Object key) {
			keyHash = System.identityHashCode(key);

			//外部リソースの設定
			resource = key.toString();

			needsRelease = true;
		}

		@Override
		public void use(Object key, Object... args) {
			System.out.println("debug:called ResourceImpl.use()");
			if (System.identityHashCode(key) != keyHash)
				throw new IllegalArgumentException("wrong key");

			//リソースの使用
			System.out.println(resource);
		}

		@Override
		public void release() {
			System.out.println("debug:called ResourceImpl.release()");
			if(needsRelease) {
				needsRelease = false;

				//リソースの開放
				resource = null;
				Runtime rt = Runtime.getRuntime();
				rt.gc();
			}
		}
	}
}
