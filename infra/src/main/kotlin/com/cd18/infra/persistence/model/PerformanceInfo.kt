import com.cd18.infra.persistence.config.model.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "performance_date")
class PerformanceInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "perf_name")
    var performanceName: String,
    @Column(name = "perf_desc")
    var performanceDescription: String,
    @Column(name = "perf_venue")
    var performanceVenue: String,
    @Column(name = "start_date")
    var startDate: String,
    @Column(name = "end_date")
    var endDate: String,
) : BaseTimeEntity()
