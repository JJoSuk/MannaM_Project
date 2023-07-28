package demo.mannam_project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="MARK4")
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;

	@Column(nullable = false, length = 50, unique = true)
	private String markname;


	private String markimage;

	@Column(nullable = false, length = 100)
	private String markaddress;

	@Column(nullable = false, length = 100)
	private String markainfo;

	@Column(nullable = false, length = 100)
	private String category;

	@Column(nullable = false, length = 100)
	private String tel;

	@Column(length = 100)
	private String latitude;

	@Column(length = 100)
	private String longitude;


	@Builder
	public Mark(String markname, String markaddress, String markainfo, String category, String tel, String latitude, String longitude, String markimage) {
		this.markname = markname;
		this.markaddress = markaddress;
		this.markainfo = markainfo;
		this.category = category;
		this.tel = tel;
		this.latitude = latitude;
		this.longitude = longitude;
		this.markimage = markimage;

	}

}