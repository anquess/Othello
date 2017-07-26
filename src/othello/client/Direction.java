package othello.client;

public enum Direction {
	UP {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()-1, xyPointer.getyPointer());
		}
	},
	DOWN {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()+1, xyPointer.getyPointer());
		}
	},
	RIGHT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer(), xyPointer.getyPointer()+1);
		}
	},
	LEFT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer(), xyPointer.getyPointer()-1);
		}
	},
	UPRIGHT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()-1, xyPointer.getyPointer()+1);
		}
	},
	UPLEFT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()-1, xyPointer.getyPointer()-1);
		}
	},
	DOWNRIGHT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()+1, xyPointer.getyPointer()+1);
		}
	},
	DOWNLEFT {
		@Override
		public XYPointer next(XYPointer xyPointer) {
			return new XYPointer(xyPointer.getxPointer()+1, xyPointer.getyPointer()-1);
		}
	};

	public abstract XYPointer next(XYPointer xyPointer);

}
