<table>
<tr>
<th>Способ создания (кратко)</th>
<th>Полный код</th>
<th>Иммутабельна?</th>
<th>Разрешен null key?</th>
<th>Разрешен null value?</th>
<th>Сколько элементов?</th>
<th>Ловушки</th>
<th>Под капотом</th>
</tr>

<tr>
<td><code>new HashMap&lt;&gt;() + put(...)</code></td>
<td>
<pre><code>    Map&lt;String, Integer&gt; hashMap = new HashMap&lt;&gt;();
    hashMap.put(KEY1, VALUE1);
    hashMap.put(KEY2, VALUE2);</code></pre>
</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">≥ 0</td>
<td style="text-align:center;">—</td>
<td>
<pre>
<code>
    Node&lt;K,V&gt;[] table;
</code>
</pre>
<pre>
<code>
    static class Node&lt;K,V&gt; implements Map.Entry&lt;K,V&gt; {
        final int hash;
        final K key;
        V value;
        Node&lt;K,V&gt; next;
        ...
</code>
</pre>
</td>
</tr>

<tr>
<td><code>Map.of(...)</code></td>
<td>
<pre><code>Map.of(
    KEY1, VALUE1,
    KEY2, VALUE2
);</code></pre>
<pre><code>Map.of();</code></pre>
</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">[ 0, 10 ]</td>
<td style="text-align:center;"><code>throws IllegalArgumentException if there are any duplicate keys</code></td>
<td>
<pre><code>
    пустая коллекция →  ImmutableCollections.MapN
    1 элемент →         ImmutableCollections.Map1
    ≥ 2 элемента →      ImmutableCollections.MapN
</code>
</pre>

<pre><code>
    ImmutableCollections.Map1 → хранит ключ и значение в отдельных полях: K и V
    ImmutableCollections.MapN → массив [KEY1, VALUE1, KEY2, VALUE2, ...]
</code>
</pre>
</td>
</tr>

<tr>
<td><code>Collections.emptyMap()</code></td>
<td>
<pre><code>Collections.emptyMap();</code></pre>
</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">—</td>
<td style="text-align:center;">—</td>
<td style="text-align:center;">0</td>
<td style="text-align:center;">не все операции модификации бросают <code>UnsupportedOperationException</code></td>
<td>
<pre><code>public class Collections {
    // ничего не хранит, вычисляет налету
    private static class EmptyMap&lt;K,V&gt; extends AbstractMap&lt;K,V&gt; implements Serializable
    ...
}</code></pre>
</td>
</tr>

<tr>
<td><code>Collections.singletonMap(KEY, VALUE)</code></td>
<td>
<pre><code>Collections.singletonMap(KEY, VALUE);</code></pre>
</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">1</td>
<td style="text-align:center;">не все операции модификации бросают <code>UnsupportedOperationException</code></td>
<td>
<pre><code>public class Collections {
    // хранит ключ и значение в отдельных полях: K и V
    private static class SingletonMap&lt;K,V&gt; extends AbstractMap&lt;K,V&gt; implements Serializable
    ...
}</code></pre>
</td>
</tr>

<tr>
<td><code>Map.ofEntries(...)</code></td>
<td>
<pre><code>Map.ofEntries(
    Map.entry(KEY1, VALUE1),
    Map.entry(KEY2, VALUE2)
);</code></pre>

<pre><code>Map.ofEntries();</code></pre>
</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">≥ 0</td>
<td style="text-align:center;"><code>throws IllegalArgumentException if there are any duplicate keys</code></td>
<td style="text-align:center;">
Аналогично <code>Map.of(...)</code>
</td>
</tr>

<tr>
<td>

<code>
Double Brace Initialization
</code>


</td>
<td>
<pre><code>new HashMap&lt;&gt;() {
    {
        put(KEY1, VALUE1);
        put(KEY2, VALUE2);
    }
};</code></pre>
</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">≥ 0</td>
<td style="text-align:center;"><a href="https://blog.jooq.org/dont-be-clever-the-double-curly-braces-anti-pattern/">Возможные проблемы с производительностью, утечками памяти и сериализацией</a></td>
<td style="text-align:center;">
<a href="https://blog.jooq.org/dont-be-clever-the-double-curly-braces-anti-pattern/">Анонимный класс с блоком инициализации экземпляра</a>
</td>
</tr>

<tr>
<td><code>Stream.of(...).collect(Collectors.toMap(...))</code></td>
<td>
<pre><code>Stream.of(new Object[][] {
    { KEY1, VALUE1 },
    { KEY2, VALUE2 },
}).collect(Collectors.toMap(
    data -> (String) data[0],
    data -> (Integer) data[1]
));</code></pre>
</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">≥ 0</td>
<td>
<pre><code>If the mapped keys contain duplicates (according to Object.equals(Object)),
an IllegalStateException is thrown when the collection operation is performed.
Use toUnmodifiableMap(Function, Function, BinaryOperator) to handle merging.</code></pre>
</td>
<td style="text-align:center;">
<code>HashMap::new</code>
</td>
</tr>

<tr>
<td><code>Stream.of(...).collect(Collectors.toUnmodifiableMap(...))</code></td>
<td>
<pre><code>Stream.of(new Object[][] {
    { KEY1, VALUE1 },
    { KEY2, VALUE2 },
}).collect(Collectors.toUnmodifiableMap(
    data -> (String) data[0],
    data -> (Integer) data[1]
));</code></pre>
</td>
<td style="text-align:center;">да</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">нет</td>
<td style="text-align:center;">≥ 0</td>
<td>
<pre><code>If the mapped keys contain duplicates (according to Object.equals(Object)),
an IllegalStateException is thrown when the collection operation is performed.
Use toUnmodifiableMap(Function, Function, BinaryOperator) to handle merging.</code></pre>
</td>
<td style="text-align:center;">
<code>Map.ofEntries(...)</code>
</td>
</tr>

</table>
