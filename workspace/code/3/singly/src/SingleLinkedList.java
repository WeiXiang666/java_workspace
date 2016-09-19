public class SingleLinkedList {
	public Node head, tail; // ������Ϊ������Ա
	public int size = 0; // ����һ���������ݣ��������ݣ�
	public final Node temp=head;

	// ���Ա�������������Ԫ�ظ���������Ч�ʵ�
	public int getSize() { // �ж�Ԫ�ص����������û��size���ݵĻ���
		return size; // ��Ҫ�����������Ȼ�����
	}

	public void clear() { // ��յ������ص�������ĳ�ʼ״̬
		head = tail = null;
		size = 0;
	}

	public void add(int data) { // ������ĩβ׷��Ԫ�أ�append����
		Node n = new Node();
		int i  = 5;
		n.data = data;
		if (size == 0) { // �������Ϊ�գ�û��һ��Ԫ��
			head = n; // head��tail��ָ�����Ԫ�ؾͿ�����
			tail = n;
		} else { // �������Ϊ��
			/*
			 * n.next = head; head = n; //ͷ�巨������ǰ�����һ��Ԫ��
			 */
			tail.next = n; // β�巨��������ĩβ����½ڵ�
			tail = n;
		}
		size++; // ����Ԫ������+1
	}

	public void add(int index, int data) { // ��ָ��λ�ò���Ԫ�أ�index��0��ʼ
		/*
		 * ���λ��index�Ƿ���Ӧ���׳�IndexOutOfBoundsException�쳣�� �쳣�����ں����½ڽ���
		 */
		if (index > size || index < 0) {
			return;
		}
		Node n1 = new Node();
		n1.data = data;
		if (size == 0) { // ����Ϊ�յ������£�
			head = n1;
			tail = n1;
		} else { // �������Ϊ��
			Node n = head; // ��ǰ�ڵ�
			Node p = head; // ��ǰ�ڵ��ǰһ���ڵ�
			for (int i = 0; i < index; i++) { // ��ʼ������ֱ���������λ��
				p = n;
				n = n.next;
			}
			if (n == p) { // ����λ�þ���0��ͷ�巨��
				n1.next = head;
				head = n1;
			} else if (n == null) { // ����λ����ĩβ��β�巨��
				tail.next = n1;
				tail = n1;
			} else { // �м�λ�ò���һ��Ԫ��
				p.next = n1;
				n1.next = n;
			}
		}
		size++; // ��Ԫ�ظ���+1
	}

	public void remove(int index) { // ɾ��ָ��λ�õ�Ԫ��
		// ע�⣬����ɾ��ĳ����
		if (index >= size || index < 0) {// �Ƿ���λ�ò�����Ӧ���׳��쳣��
			return; // �ڴ˴��򵥵ķ��ش���ʲôҲ����
		}
		if (size == 1) { // ��������ֻ��һ��Ԫ�أ�ɾ��
			head = tail = null;
		} else if (index == 0) { // ��ֹ��һ��Ԫ�أ����ɾ����һ��Ԫ��
			head = head.next; // ���е�һ��Ԫ�ص�ɾ������
		} else {
			Node n = head; // �������ɾ����һ��Ԫ�أ������
			Node p = head; // ����λ��
			for (int i = 0; i < index; i++) {
				p = n;
				n = n.next;
			}
			p.next = n.next; // ���и�ָ��λ�õ�ɾ������
			if (p.next == null) { // ���ɾ������ĩβ�ڵ㣬��ôp.nextΪ��
				tail = p; // ����Ҫ����tail����
			}
		}
		size--; // Ԫ�صĸ���-1
	}

	public void printElements() { // ����������������ݣ�����������
		if (size == 0) {
			System.out.println("empty single linkedlist!");
		} else {
			Node n = head;
			System.out.print("elements: ");
			while (n != null) { // ѭ����������
				if (n == tail) { // println����������Զ���ӻس�����
					System.out.println(n.data); // ����Ԫ������󣬻س�����
				} else { // print����������Զ���ӻس�����
					System.out.print(n.data + ", ");
				}
				n = n.next; // ������ı����������½ڵ��쳣��Ҳ�ǵ�����
			}
		}
	}

	public void printFirst() { // ���ͷ�ڵ�Ԫ����Ϣ
		if (head != null) {
			System.out.println("first = " + head.data);
		} else {
			System.out.println("no first element.");
		}
	}

	public void printLast() { // ���ĩβ�ڵ�Ԫ����Ϣ
		if (tail != null) {
			System.out.println("last = " + tail.data);
		} else {
			System.out.println("no last element.");
		}
	}

	public void printSize() { // ��ӡ���������Ĵ�С
		System.out.println("size = " + size);
	}

	public void printAll() { // ���ȫ����Ϣ
		System.out.println("-----------------------------------------------------------");
		printFirst();
		printLast();
		printSize();
		printElements();
	}

	public void bubbleSort() { // ð�������㷨��
		if (size < 2) {
			return;
		}
		Node tail2 = tail; // ��һ�飬��ͷ�Ƚϵ����һ���ڵ㣬�Ƚϳ����
		while (tail2 != head) { // ��Ԫ�طŵ����һ���ڵ㣬�ڶ��࣬��ͷ�Ƚϵ�
			Node t1 = head; // ���ڶ���Ԫ�أ�tail2��ÿ�αȽϵ����һ��Ԫ��
			Node t2 = t1.next; // tail2��ֵ����ǰ�ƣ�ֱ��head
			Node p1 = null;
			while (t1 != tail2) { // �ڲ�ѭ����ÿ�ζ��Ǵ�ͷ�Ƚϵ�tail2
				if (t1.data > t2.data) { // ʹ��t1,t2��ָ�����ڵıȽ�����
					swapData(t1, t2); // �������ݣ�������ĳ���
				}
				p1 = t1; // һ�˱Ƚ���ϣ�p1ָ����һ�˵����һ��Ԫ��
				t1 = t2; // t1, t2�����
				t2 = t2.next;
			}
			tail2 = p1; // һ�˽�������ʼ��һ�˱Ƚϣ��趨�µ�ĩβλ��
		}
	}

	public void swapData(Node t1, Node t2) { // ���������ڵ������
		if (t1 == null || t2 == null) { // ���ǽ������е������ڵ�
			return;
		}
		int temp = t1.data;
		t1.data = t2.data;
		t2.data = temp;
	}

	public void insertionSort() {
		// ֱ�Ӳ�������������Ӹ�����ʵ�ֲ��ã�����ѭ��
		if (size < 2) {
			return;
		}
		Node p1 = head.next; // p1ʼ��ָ��δ�������ݵĵ�һ��Ԫ��λ��
		while (p1 != null) { // Ĭ��headָ��ĵ�һ��Ԫ�����ź���
			// p1��Ϊ�գ��Ѿ�ָ�������ĩβ���������
			Node p2 = head; // p2ָ���ź����Ԫ�أ������ƶ��仯������
			while (p1 != p2) { // �Ƚ������Ƿ�ȫ���ȽϽ�����
				if (p1.data < p2.data) {
					int i1 = p1.data;
					while (p2 != p1.next) { // ���ѭ��������ɡ����롱
						int i2 = p2.data; // ��Ҫ����
						p2.data = i1;
						i1 = i2;
						p2 = p2.next;
					}
					break;
				} else {
					p2 = p2.next; // �粻��Ҫ���룬����ͬ���������ݵ�
					// ��һ��Ԫ�ؽ��бȽϣ�ֱ���Ƚ����
				}
			}
			p1 = p1.next;
		}
	}

	public int indexOf(int data) { // ���������ڵ������е�һ�εĳ���λ�õ��±�
		int index = -1; // ��������ڵ������в����ڣ��򷵻�-1
		if (size == 0) {
			return index;
		}
		Node n = head;
		index = 0;
		while (n != null) {
			if (n.data == data) {
				return index;
			} else {
				index++;
			}
			n = n.next;
		}
		return -1;
	}
	public void orderShow(Node head)
	{
		if(head==null)
		{
		
			return;
		}
		System.out.print(head.data+" ");
		orderShow(head.next);
	}
	public void reverseShow(Node head)
	{
		if(head==null)
			return;
		reverseShow(head.next);
		System.out.print(head.data+" ");
	}
	public static Node reverseList(Node head)
	{
		if(head==null||head.next==null)
			return head;
		Node rehead=reverseList(head.next);
		head.next.next=head;
		head.next=null;
		return rehead;
		
	}


	public static void main(String[] args) { // �����ǲ��Գ���
		SingleLinkedList sll = new SingleLinkedList(); // �������������
		sll.add(5); // ���Ԫ��
		sll.add(9);
		sll.add(3);
		sll.add(85);
		sll.add(89);
		sll.orderShow(sll.head);
		sll.head=reverseList(sll.head);
		sll.orderShow(sll.head);
//		sll.printAll(); // ���ȫ����Ϣ
//		sll.remove(0); // ɾ����һ��Ԫ��(ͷԪ��)
//		sll.printAll(); // �ٴ�����������

		// ����ĳ��������������򣬲��ҡ�
		SingleLinkedList s2 = new SingleLinkedList();
		s2.add(49);
		s2.add(38);
		s2.add(65);
		s2.add(97);
		s2.add(76);
		s2.add(13);
		s2.add(27);
		s2.add(49);
//		s2.insertionSort(); // ���ò������򷽷�������һ�ڶ���
//		s2.printAll();
		s2.orderShow(s2.head);
		s2.reverseShow(s2.head);
		

		s2.clear();
		s2.add(49);
		s2.add(38);
		s2.add(65);
		s2.add(97);
		s2.add(76);
		s2.add(13);
		s2.add(27);
		s2.add(49);
//		s2.bubbleSort(); // ����ð�����򷽷�������һ�ڶ���
//		s2.printAll();

//		System.out.println(s2.indexOf(5)); // �������ݲ��ҷ���
	}
}
