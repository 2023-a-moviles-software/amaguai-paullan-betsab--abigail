import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class SQLite {
    val kClass = Class.forName("org.sqlite.JDBC").kotlin
    private val connection : Connection = DriverManager.getConnection("jdbc:sqlite:Deber3.db")
    private val statement : Statement = connection.createStatement()

    fun createTable() {
        val checkTableCategoria = "SELECT name FROM sqlite_master WHERE type='table' AND name='CATEGORIA';"
        val checkTableNota = "SELECT name FROM sqlite_master WHERE type='table' AND name='NOTA'"

        val categoriaTableExist = statement.executeQuery(checkTableCategoria).next()
        val notaTableExist = statement.executeQuery(checkTableNota).next()

        if (!categoriaTableExist) {
            val createCategoriaTable = """
                CREATE TABLE CATEGORIA (
                id_categoria            INTEGER   PRIMARY KEY NOT NULL,
                nombre_categoria        VARCHAR(20),
                descripcion_categoria   VARCHAR(100),
                prioridad_categoria     INTEGER
                )
            """.trimIndent()
            statement.executeQuery(createCategoriaTable)
        }

        if (!notaTableExist) {
            val createNotaTable = """
                CREATE TABLE NOTA (
                id_nota             INTEGER    PRIMARY KEY AUTOINCREMENT,
                id_categoria        INTEGER    NOT NULL,
                nombre_nota         VARCHAR(20),
                descripcion_nota    VARCHAR(100),
                prioridad_nota      INTEGER,
                FOREIGN KEY (id_nota) REFERENCES CATEGORIA (id_nota)
                )
            """.trimIndent()
            statement.executeQuery(createNotaTable)
        }
    }
        fun close(){
            statement.close()
            connection.close()
        }

        //Funciones para Categorias
        fun crearCategoria(idCategoria : Int, nombreCategoria:String, descripcionCategoria:String, prioridadCategoria:Int):Boolean {
            val query = """
                INSERT INTO CATEGORIA (id_categoria, nombre_categoria, descripcion_categoria, prioridad_categoria)
                VALUES ('$idCategoria','$nombreCategoria','$descripcionCategoria','$prioridadCategoria')
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e: Exception){
                return false
            }
        }

        fun consultarCategorias() : ArrayList<Categoria>{
            var categorias = ArrayList<Categoria>()
            val query = """
                SELECT * FROM CATEGORIA
            """.trimIndent()
            try {
                val result = statement.executeQuery(query)
                while (result.next()){
                    categorias.add(Categoria(result.getInt(1),result.getString(2),
                        result.getString(3), result.getInt(4)))
                }
                return categorias
                } catch (e: Exception){
                return  categorias
                }
        }

        fun consultarCategoriaPorId(idCategoria: Int) : Categoria? {
            val query = """
                SELECT * FROM CATEGORIA
                WHERE id_categoria = '$idCategoria'
            """.trimIndent()
            val result = statement.executeQuery(query)
            try {
                if (result.next()){
                    return Categoria(result.getInt(1),result.getString(2),
                        result.getString(3), result.getInt(4))
                }
            } catch (e: Exception){
                println("A ocurrido un error : ${e.toString()}")
            } finally {
                result.close()
            }
            return null
        }



        fun eliminarCategoriaPorId(idCategoria: Int):Boolean {
            val query = """
                DELETE FROM CATEGORIA
                WHERE id_categoria = '$idCategoria'
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e : Exception){
                return false
            }
        }

        fun actualizarCategoria(idCategoria: Int, nuevoNombreCategoria: String?, nuevoDescripcionCategoria: String?, nuevoPrioridadCategoria: Int?): Boolean {
            val query = """
                UPDATE CATEGORIA
                SET nombre_categoria = ?, descripcion_categoria = ?, prioridad_categoria = ?
                WHERE id_categoria = ?
            """.trimIndent()
            try {
                val preparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1,nuevoNombreCategoria)
                preparedStatement.setString(2,nuevoDescripcionCategoria)
                preparedStatement.setInt(3,nuevoPrioridadCategoria ?: 0)
                preparedStatement.setInt(4,idCategoria)

                preparedStatement.executeUpdate()
                preparedStatement.close()
                return true
            } catch (e: Exception){
                return false
            }
        }


    //Funciones de NOTAS
        fun crearNota(idCategoria: Int, nombreNota: String, descripcionNota: String, prioridadNota: Int) : Boolean{
            val query = """
                INSERT INTO NOTA (id_categoria, nombre_nota, descripcion_nota, prioridad_nota)
                VALUES ('$idCategoria','$nombreNota','$descripcionNota','$prioridadNota')
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e : Exception){
                return false
            }
        }

        fun consultarNotas(idCategoria: Int) : ArrayList<Nota>{
            var notas = ArrayList<Nota>()
            val query = """
                SELECT * FROM NOTA
                WHERE id_categoria = '$idCategoria'
            """.trimIndent()
            try {
                val result = statement.executeQuery(query)
                while (result.next()){
                    notas.add(Nota(result.getInt(1), result.getInt(2),result.getString(3),
                        result.getString(4), result.getInt(5)))
                }
                return notas
            } catch (e: Exception){
                return  notas
            }
        }

        fun consultarNotaPorId(idCategoria: Int, idNota: Int) : Nota? {
            val query = """
                SELECT * FROM NOTA
                WHERE id_categoria = '$idCategoria' AND id_nota = '$idNota'
            """.trimIndent()
            val result = statement.executeQuery(query)
            try {
                if (result.next()){
                    return Nota(result.getInt(1),result.getInt(2),
                        result.getString(3), result.getString(4), result.getInt(5))
                }
            } catch (e: Exception){
                println("A ocurrido un error : ${e.toString()}")
            } finally {
                result.close()
            }
            return null
        }

        fun eliminarNotaPorId(idCategoria: Int, idNota: Int) : Boolean{
            val query = """
                DELETE FROM NOTA
                WHERE id_categoria = '$idCategoria' AND id_nota = '$idNota'
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e: Exception) {
                return false
            }
        }

        fun actualizarNota(idCategoria: Int, idNota: Int, nuevoNombreNota: String?, nuevoDescripcionNota: String?, nuevaPrioridadNota: Int?) : Boolean{
            val query = """
                UPDATE NOTA
                SET nombre_nota = ?, descripcion_nota = ?, prioridad_nota = ?
                WHERE id_categoria = ? AND id_nota = ?
            """.trimIndent()
            try {
                val preparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1,nuevoNombreNota)
                if (nuevoDescripcionNota != null) {
                    preparedStatement.setString(2, nuevoDescripcionNota)
                }
                preparedStatement.setInt(3,nuevaPrioridadNota ?: 0)
                preparedStatement.setInt(4, idCategoria)
                preparedStatement.setInt(5, idNota)

                preparedStatement.executeUpdate()
                preparedStatement.close()
                return true
            } catch (e: Exception){
                return false
            }
        }



}