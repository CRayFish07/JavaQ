var BMapLib = window.BMapLib = BMapLib || {};
(function() {
	var d = BMapLib.CurveLine = function(f, g) {
		var h = getCurvePoints(f);
		var e = new BMap.Polyline(h, g);
		e.addEventListener("lineupdate", function() {
			if (this.isEditing) {
				this.enableEditing()
			}
		});
		e.editMarkers = [];
		e.points = f;
		e.enableEditing = c;
		e.disableEditing = b;
		e.getPath = a;
		return e
	};
	getCurvePoints = function(f) {
		var h = [];
		for ( var e = 0; e < f.length - 1; e++) {
			var g = getCurveByTwoPoints(f[e], f[e + 1]);
			if (g && g.length > 0) {
				h = h.concat(g)
			}
		}
		return h
	};
	getCurveByTwoPoints = function(z, w) {
		if (!z || !w || !(z instanceof BMap.Point)
				|| !(w instanceof BMap.Point)) {
			return null
		}
		var o = function(h) {
			return 1 - 2 * h + h * h
		};
		var n = function(h) {
			return 2 * h - 2 * h * h
		};
		var m = function(h) {
			return h * h
		};
		curveCoordinates = [];
		var k = 30;
		var x = false;
		var r, y, l, e, A, u, p;
		var s = [];
		var v = 0;
		var q = 0;
		if (typeof (w) == "undefined") {
			if (typeof (curveCoordinates) != "undefined") {
				curveCoordinates = []
			}
			return
		}
		var g = parseFloat(z.lat);
		var f = parseFloat(w.lat);
		var C = parseFloat(z.lng);
		var B = parseFloat(w.lng);
		if (B > C) {
			if (parseFloat(B - C) > 180) {
				if (C < 0) {
					C = parseFloat(180 + 180 + C)
				}
			}
		}
		if (C > B) {
			if (parseFloat(C - B) > 180) {
				if (B < 0) {
					B = parseFloat(180 + 180 + B)
				}
			}
		}
		u = 0;
		p = 0;
		if (f == g) {
			r = 0;
			y = C - B
		} else {
			if (B == C) {
				r = Math.PI / 2;
				y = g - f
			} else {
				r = Math.atan((f - g) / (B - C));
				y = (f - g) / Math.sin(r)
			}
		}
		if (p == 0) {
			p = (r + (Math.PI / 5))
		}
		l = y / 2;
		A = l * Math.cos(p) + C;
		e = l * Math.sin(p) + g;
		for (v = 0; v < k + 1; v++) {
			curveCoordinates.push(new BMap.Point((C * o(q) + A * n(q)) + B
					* m(q), (g * o(q) + e * n(q) + f * m(q))));
			q = q + (1 / k)
		}
		return curveCoordinates
	};
	function c() {
		if (this.map) {
			this.disableEditing();
			for ( var g = 0; g < this.points.length; g++) {
				var e = new BMap.Marker(
						this.points[g],
						{
							icon : new BMap.Icon(
									"http://api.map.baidu.com/library/CurveLine/1.5/src/circle.png",
									new BMap.Size(16, 16)),
							enableDragging : true,
							raiseOnDrag : true
						});
				var f = this;
				e.addEventListener("dragend", function() {
					f.points.length = 0;
					for ( var h = 0; h < f.editMarkers.length; h++) {
						f.points.push(f.editMarkers[h].getPosition())
					}
					var j = getCurvePoints(f.points);
					f.setPath(j)
				});
				e.index = g;
				this.editMarkers.push(e);
				this.map.addOverlay(e)
			}
		}
		this.isEditing = true
	}
	function b() {
		this.isEditing = false;
		for ( var e = 0; e < this.editMarkers.length; e++) {
			this.map.removeOverlay(this.editMarkers[e]);
			this.editMarkers[e] = null
		}
		this.editMarkers.length = 0
	}
	function a() {
		return this.points
	}
})();