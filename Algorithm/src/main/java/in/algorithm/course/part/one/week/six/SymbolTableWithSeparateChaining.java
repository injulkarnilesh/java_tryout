package in.algorithm.course.part.one.week.six;

public class SymbolTableWithSeparateChaining<Key, Value> implements SymbolTable<Key, Value> {

    private final int size;
    private final Node<Key, Value> chains[];

    private SymbolTableWithSeparateChaining(final int size) {
        this.size = size;
        this.chains = new Node[size];
    }

    public static <Key, Value> SymbolTable<Key, Value> createNew(final int size) {
        return new SymbolTableWithSeparateChaining<>(size);
    }

    @Override
    public void put(final Key key, final Value value) {
        final int chainIndex = getChainIndex(key);
        for(Node<Key, Value> match = this.chains[chainIndex]; match !=null; match = match.getNext()) {
            if (match.getKey().equals(key)) {
                match.setValue(value);
                return;
            }
        }
        final Node<Key, Value> newNode = Node.createNew(key, value);
        newNode.setNext(this.chains[chainIndex]);
        this.chains[chainIndex] = newNode;
    }

    private int getChainIndex(final Key key) {
        return key.hashCode() % size;
    }

    @Override
    public Value get(final Key key) {
        final int chainIndex = getChainIndex(key);
        for (Node<Key, Value> match = this.chains[chainIndex]; match != null; match = match.getNext()) {
            if (match.getKey().equals(key)) {
                return match.getValue();
            }
        }
        return null;
    }
}
