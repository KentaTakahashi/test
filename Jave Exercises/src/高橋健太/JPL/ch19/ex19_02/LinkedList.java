package ��������.JPL.ch19.ex19_02;


/**
 *
 * @author User
 *
 */
public class LinkedList {

	/** ���X�g���ɕۊǂ���I�u�W�F�N�g */
	private Object mObj = null;
	/** ���̃��X�g��ۊǂ��郊�X�g */
	private LinkedList next = null;

	/** �����������l�Ƃ��Đݒ肷�� */
	public LinkedList(Object obj, LinkedList next) {
		this.mObj = obj;
		this.next = next;
	}
	/** �����������l�Ƃ��Đݒ肷�� */
	public LinkedList(Vehicle obj) {
		this.mObj = obj;
	}
	/**
	 * ���X�g���ɕۊǂ��Ă���I�u�W�F�N�g��Ԃ�
	 * @return ���X�g���ɕۊǂ��Ă���I�u�W�F�N�g
	 */
	public Object get() {
		return mObj;
	}
	/**
	 * ���̃��X�g��Ԃ�
	 * @return ���̃��X�g
	 */
	public LinkedList getNext() {
		return next;
	}
	/**
	 * �I�u�W�F�N�g��ݒ肷��
	 * @param obj �ݒ肷��I�u�W�F�N�g
	 */
	public void set(Object obj) {
		mObj = obj;
	}
	/**
	 * ���̃��X�g��ݒ肷��
	 * @param next ���̃��X�g
	 */
	public void setNext(LinkedList next) {
		this.next = next;
	}
	/**
	 * �ۑ����Ă���I�u�W�F�N�g��String�^�ɂ��ĕԂ�
	 * @return �ۑ����Ă���I�u�W�F�N�g��String�^
	 */
	public String toString() {
		return mObj.toString();
	}
	/**
	 * ���X�g�ɕۑ����Ă���v�f����Ԃ�
	 * @return ���X�g�ɕۑ����Ă���v�f��
	 */
	public int countElement() {
		int count = 0;

		LinkedList list = this;

		while(list != null) {
			count++;
			list = list.next;

			if(list == this)break;
		}

		return count;
	}
}
